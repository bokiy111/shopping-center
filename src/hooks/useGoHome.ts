import { useNavigate } from 'react-router';
import { useMemoizedFn } from 'ahooks';
import { useUser } from '../models/account/hooks';
import { UserRole } from '../data/enums';
import { logger } from '../common/logger';

export const useGetDefaultUrl = () => {
  const user = useUser();

  return useMemoizedFn(() => {
    const roleSystem = {
      [UserRole.SELLER]: '/s',
    };
    if (user?.role) {
      return roleSystem[user.role];
    }
    logger.error({
      key: 'GetDefaultUrl Error',
      content: user,
    });
    return '';
  });
};

export const useGoHome = () => {
  const navigate = useNavigate();

  const getDefaultUrl = useGetDefaultUrl();
  return useMemoizedFn(() => {
    const url = getDefaultUrl();
    return navigate(url);
  });
};
