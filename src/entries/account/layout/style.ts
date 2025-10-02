import { css } from '@linaria/core';

export const wrapper = css`
  width: 100%;
  height: 100%;
  display: flex;
  justify-content: center;  
  align-items: center;
`;

export const box = css`
  border-radius: 20px;
  height: 600px;
  width: 100%;
  max-width: 1200px;
  display: flex;
  justify-content: space-between;
    
  .left {
    border-radius: 20px 0 0 20px;
    height: 100%;
    width: 50%;
      
    img {
      border-radius: 20px 0 0 20px;
      height: 100%;
      width: 100%;
      object-fit: cover;
    }
  }

  .right {
    border-radius: 0 20px 20px 0;
    height: 100%;
    width: 50%;
    padding: 20px;
    background-color: #f5f5f5; 
    display: flex;
    justify-content: center;
    align-items: center;
    gap: 20px;
  }
`;
