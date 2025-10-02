import { useEffect, useRef, useState } from 'react';
import { HomeCarousel } from './HomeCarousel';
import { homeWrapper } from './style';
import { CategoryEntry } from './CategoryEntry';

export const Home = () => {
  const homeWrapperRef = useRef<HTMLDivElement>(null);
  const [width, setWidth] = useState<number>(0);

  useEffect(() => {
    const updateWidth = () => {
      if (homeWrapperRef.current) {
        setWidth(homeWrapperRef.current.offsetWidth);
      }
    };

    updateWidth();

    const resizeObserver = new ResizeObserver(updateWidth);
    const currentRef = homeWrapperRef.current;
    if (currentRef) {
      resizeObserver.observe(currentRef);
    }

    return () => {
      if (currentRef) {
        resizeObserver.unobserve(currentRef);
      }
    };
  }, []);

  return (
    <div className={homeWrapper} ref={homeWrapperRef}>
      <HomeCarousel width={width} />
      <CategoryEntry />
    </div>
  );
};
