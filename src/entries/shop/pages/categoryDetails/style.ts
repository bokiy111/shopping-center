import { css } from '@linaria/core';

export const goodsImage = css`
  width: 100%;
  height: 300px;
  object-fit: cover;
`;

export const goodsDetailsWrapper = css`
  width: 100%;
  height: 500px;
  
  display: flex;
  justify-content: center;
  align-items: center;
  gap: 10px;
    
  .left {
    width: 50%;

    .carousel {
      width: 100%;
        
      .goodsImage {
          width: 100%;
          height: 500px;
          object-fit: cover;
      }
    }
  }
  
  .right {
    width: 50%;
    display: flex;
    flex-direction: column;
    gap: 10px;
    justify-content: center;
      
    .goods {
        
    }
    
    .seller{
      .info {
        display: flex;
        gap: 20px
      }
    }
  }
`;
