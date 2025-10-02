export const isArray = <T = any>(a?: T[] | T): a is Array<T> => {
  return !!a && Array.isArray(a);
};
