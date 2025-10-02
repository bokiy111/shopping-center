import { css } from '@linaria/core';

export const headerStyle = css`
    width: 100%;
    border-radius: 10px;
    height: 60px;
    padding: 0 15px;
    display: flex;
    justify-content: space-between;
    align-items: center;
    background-color: #fff1d4;
`;

export const headerStyleAtMedia = css`
    @media (min-width: 991px) {
        padding-inline: 26px;
    }
`;

export const navLeftStyle = css`
  user-select: none;
  &:hover {
    cursor: pointer;
  }
`;

export const search = css`
  max-width: 400px;
  width: 100%;
`;

export const layoutTotalStyle = css`
  overflow: auto;
  width: 100%;
  min-height: 100vh;
  padding: 15px;
  display: flex;
  flex-direction: column;
  gap: 10px;
`;

export const layoutSiderAndContentStyle = css`
  padding-top: 52px;
`;

export const layoutContentStyle = css`
  padding: 12px;
  border-radius: 10px;
  background-color:  #fff1d4;
  width: 100%;
  display: flex;
  flex-direction: column;
  gap: 10px;
`;

export const contentStyle = css`
  width: 100%;
  max-width: 1200px;
  margin: 0 auto;
  display: flex;
  flex-direction: column;
  align-items: center;
`;

export const titleFontStyle = css`
  font-size: 16px;
  font-weight: 550;
`;
