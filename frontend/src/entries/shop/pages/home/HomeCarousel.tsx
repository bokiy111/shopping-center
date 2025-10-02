import { Carousel } from 'antd';
import spring from '../../../../assets/spring.jpg';
import winter from '../../../../assets/winter.jpg';
import {carousel, carouselImg} from './style';

export interface HomeCarouselProps {
  width: number;
}

const bannerList = [
  {
    key: 'spring',
    url: spring as string,
  },
  {
    key: 'winter',
    url: winter as string,
  },
];

export const HomeCarousel = ({
  width,
}: HomeCarouselProps) => {
  return (
    <Carousel
      dotPosition={'top'}
      autoplay
      style={{
        width: width,
      }}
      className={carousel}
    >
      {bannerList.map((item) =>
        <img
          key={item.key}
          src={item.url}
          alt="carousel"
          className="carouselImg"
        />)}
    </Carousel>
  );
};
