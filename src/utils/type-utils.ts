/**
 * 数组项类型
 */
export type ArrayItemType<T extends any[]> = T extends (infer P)[] ? P : never;

/**
 * 数组类型转元组类型
 */
export type ArrayToTuple<T extends any[]> =
  T extends [infer R, ...infer Rest]
    ? [R, ArrayItemType<Rest>]
    : T extends [infer P]
      ? [P]
      : T;

/**
 * 数组类型转联合类型
 */
export type ArrayToUnion<T extends any[]> = T[number];

export type TupleToUnion<T> =
  T extends [infer R, ...infer Rest]
    ? R | TupleToUnion<Rest>
    : T extends [infer S]
      ? S
      : never;

export type ReadonlyTupleToUnion<T> =
  T extends readonly [infer R, ...infer Rest]
    ? R | TupleToUnion<Rest>
    : T extends readonly [infer S]
      ? S
      : never;

export type PartialKey<T extends Record<string, any>> = {
  [P in keyof T]: T[P] | never;
};

export type ShouldWith<Condition extends boolean, Type> = Condition extends true ? Type : unknown;
