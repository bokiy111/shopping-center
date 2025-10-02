import { ReactNode, useEffect, useState } from 'react';
import { useRequest } from 'ahooks';
import { message, Spin } from 'antd';
import { getToken } from '../token';
import { requestMyInfo } from '../../services/requests/user';
import { useSetUser, useUser } from '../../models/account/hooks';
import { useGetDefaultUrl } from '../../hooks/useGoHome';
import { useUniRedirect } from '../../hooks/useUniRedirect';

export const AutoLogin = ({ children }: { children: ReactNode }) => {
  const token = getToken();
  const setUser = useSetUser();
  const user = useUser();
  const getDefaultUrl = useGetDefaultUrl();
  const uniRedirect = useUniRedirect();
  const [loading, setLoading] = useState(false);

  useRequest(requestMyInfo, {
    onSuccess(res) {
      setUser(res);
    },
    onError(err) {
      message.error(err.message);
    },
    ready: !!token,
  });

  useEffect(() => {
    if (token) {
      setLoading(true);
    }
  }, [token]);

  useEffect(() => {
    if (user?.id) {
      message.success('登录成功');
      const defaultUrl = getDefaultUrl();
      uniRedirect(defaultUrl);
      setLoading(false);
    }
  }, [getDefaultUrl, uniRedirect, user]);

  if (loading) {
    return <Spin />;
  }

  return <>{children}</>;
};
