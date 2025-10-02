import { css } from '@linaria/core';

export const homeWrapper = css`
  width: 100%;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
`;

export const carousel = css`
  .carouselImg {
    width: 100%;
    height: 600px;
    object-fit: cover;
    border-radius: 10px 10px 0 0;
    position: relative;
  }
  
  &::before {
    content: "";
    position: absolute;
    top: 0;
    left: 0;
    width: 100%;
    height: 100%;
    background: linear-gradient(to bottom, rgba(255, 255, 255, 0) 0%, #fff1d4 100%);
    border-radius: 10px 10px 0 0;
    z-index: 1; /* 确保遮罩在图片之上 */
  }
`;

export const homeEntry = css`
  width: 100%;
  z-index: 10;
  margin-top: -300px;

  .entryImg {
    width: 100%;
    height: 100%;
    object-fit: cover;
  }
 
`;

