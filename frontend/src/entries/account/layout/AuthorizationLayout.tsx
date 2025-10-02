import React from 'react';
import winter from '../../../assets/winter.jpg';
import { box, wrapper } from './style';

export interface AuthorizationLayoutProps {
  children: React.ReactNode;
}

export const AuthorizationLayout = ({
  children,
}: AuthorizationLayoutProps) => {
  return (
    <div className={wrapper}>
      <div className={box}>
        <div className="left">
          <img alt="pic"
            src={winter as string} />
        </div>
        <div className="right">
          {children}
        </div>
      </div>
    </div>
  );
};
