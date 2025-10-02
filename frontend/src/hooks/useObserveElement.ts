import { useCreation } from 'ahooks';
import { useEffect, useState } from 'react';

export type ObserveHandler = (entry: ResizeObserverEntry) => void;

export const useObserveElement = (observe: ObserveHandler) => {
  const [wrapper, setWrapper] = useState<HTMLElement | null>(null);

  const observer = useCreation(() => {
    return new ResizeObserver((entries) => {
      for (const entry of entries) {
        observe(entry);
      }
    });
  }, [observe]);

  useEffect(() => {
    if (!wrapper) {
      return;
    }

    observer.observe(wrapper);

    return () => {
      observer.unobserve(wrapper);
    };
  }, [wrapper, observer]);

  return {
    wrapper,
    setWrapper,
  };
};
