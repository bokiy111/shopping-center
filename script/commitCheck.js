// Invoked on the commit-msg git hook by yorkie.

import chalk from 'chalk'
import fs from 'fs'

const msgPath = process.env.GIT_PARAMS ?? './.git/COMMIT_EDITMSG'
const msg = fs.readFileSync(msgPath, 'utf-8').trim()

const releaseRE = /^v\d/

const commitRE =
  /^(revert: )?(feat|fix|docs|dx|refactor|perf|test|workflow|build|ci|chore|types|wip|release|deps|style)(\(.+\))?: .{1,50}/

const bindRequirements = /to #\d+/

if (!releaseRE.test(msg) && !commitRE.test(msg) && !/^Merge remote-tracking branch/.test(msg)) {
  console.log()
  console.error(
    `  ${chalk.white(' ERROR ')} ${chalk.red(
      `invalid commit message format.`,
    )}\n\n` +
    chalk.red(
      `  Proper commit message format is required for automated changelog generation. Examples:\n\n`,
    ) +
    `    ${chalk.green(`feat: add 'comments' option`)}\n` +
    `    ${chalk.green(`fix: handle events on blur (close #28)`)}\n\n` +
    chalk.red(`  See .github/commit-convention.md for more details.\n`),
  )
  process.exit(1)
}

// if (!bindRequirements.test(msg)) {
//   console.log()
//   console.error(
//     `  ${chalk.bgRed.white(' ERROR ')} ${chalk.red(
//       `Invalid commit message.`,
//     )}\n\n` +
//     `  The commit message should in format: ${chalk.green(
//       'feat/fix: to #xxxx',
//     )}\n\n`,
//   )
//
//   process.exit(1)
// }

console.log()
console.log(
  `  ${chalk.bgGreen.white(' SUCCESS ')} ${chalk.green(
    `[Bind Requirements] successfully commit.`,
  )}\n\n`,
)
