import { debug as construct } from 'debug';

const PREFIX = 'debug:log';

export function debugFactory(scope: string) {
  const debug = construct(PREFIX).extend(scope);

  const log = debug.extend('log');
  const info = debug.extend('info');
  const error = debug.extend('error');
  const warn = debug.extend('warn');

  return { log, info, error, warn };
}


export default debugFactory('main');
