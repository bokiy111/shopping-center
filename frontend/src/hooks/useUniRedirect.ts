import { useMemoizedFn } from 'ahooks';
import { useLocation, useNavigate } from 'react-router';


export const useUniRedirect = () => {
  const navigate = useNavigate();
  const { search } = useLocation();
  const params = new URLSearchParams(search);
  const redirectPath = params.get('redirect');
  const path = decodeURIComponent(redirectPath || '');

  /**
   * 如果没有 redirect，则兜底跳转的位置
   */
  return useMemoizedFn((fallback: string) => {
    if (path) {
      navigate(path);
    } else {
      navigate(fallback);
    }
  });
};
