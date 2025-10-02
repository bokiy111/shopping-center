import { atom, useAtomValue, useSetAtom } from 'jotai';
import { Labels } from '../../data/base-types';

export const labelsAtom = atom<Labels>([]);

export function useLabels() {
  return useAtomValue(labelsAtom);
}

export function useSetLabels() {
  return useSetAtom(labelsAtom);
}
