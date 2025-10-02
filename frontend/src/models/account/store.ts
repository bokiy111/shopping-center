import { atom } from 'jotai';
import { User } from '../../data';
import { getToken } from '../../authorization/token';

export const accountAtoms = {
  selfInfo: atom<User | undefined>(undefined),
  token: atom<string | null>(getToken()),
};
