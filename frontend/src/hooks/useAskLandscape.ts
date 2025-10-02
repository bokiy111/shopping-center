import { useMemoizedFn, useMount } from 'ahooks';
import { message } from 'antd';
import { isMobile } from '../common/environment';
import { useObserveElement } from './useObserveElement';

export const useAskLandscape = () => {
  const handleObserve = useMemoizedFn((entry: ResizeObserverEntry) => {
    if (entry.contentRect.width < 800 && isMobile()) {
      message.info({
        content: '检测到您的屏幕较窄，建议您旋转屏幕可以获得更好的体验',
        duration: 3,
      });
    }
  });

  const { setWrapper } = useObserveElement(handleObserve);

  useMount(() => {
    setWrapper(window.document.body);
  });
};
