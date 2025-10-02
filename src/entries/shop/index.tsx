import { Navigate, Route, Routes } from 'react-router-dom';
import { ShopLayout } from './layout';
import { Home } from './pages/home';
import { CategoryDetails } from './pages/categoryDetails';

export function Shop() {
  return (
    <ShopLayout>
      <Routes>
        <Route path="home" element={<Home />} />
        <Route path="category/:categoryParam" element={<CategoryDetails />} />
        <Route path="*" element={<Navigate to="home" />} />
      </Routes>
    </ShopLayout>
  );
}
