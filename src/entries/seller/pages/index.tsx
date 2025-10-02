import { Navigate, Route, Routes } from 'react-router-dom';
import { SellerLayout } from '../layout';
import { Home } from './home';

export function Seller() {
  return (
    <SellerLayout>
      <Routes>
        <Route path="home" element={<Home />} />
        <Route path="*" element={<Navigate to="home" />} />
      </Routes>
    </SellerLayout>
  );
}
