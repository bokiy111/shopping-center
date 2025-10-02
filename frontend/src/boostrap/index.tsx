import { ReactNode } from 'react';
import { useRequest } from 'ahooks';
import { message } from 'antd';
import { useSetUser } from '../models/account/hooks';
import { requestMyInfo } from '../services/requests/user';
import { getToken } from '../authorization/token';

export const Bootstrap = ({ children }: { children: ReactNode }) => {
  // const setLabels = useSetLabels();
  const token = getToken();
  const setUser = useSetUser();

  // useRequest(requestAllLabels, {
  //   onSuccess(res) {
  //     setLabels(res.list);
  //   },
  //   onError(err) {
  //     message.error(err.message);
  //   },
  //   ready: !!token,
  // });

  useRequest(requestMyInfo, {
    onSuccess(res) {
      setUser(res);
    },
    onError(err) {
      message.error(err.message);
    },
    ready: !!token,
  });

  return <>{children}</>;
};
