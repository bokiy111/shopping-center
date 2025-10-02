import { Navigate, Route, Routes } from 'react-router-dom';
import { Login } from './pages/login';
import { Register } from './pages/register';
import { AccountLayout } from './layout';
import {AuthorizationLayout} from "./layout/AuthorizationLayout";

export function Account() {
  return (
    <AccountLayout>
      <AuthorizationLayout>
        <Routes>
          <Route path="login" element={<Login />} />
          <Route path="register" element={<Register />} />
          <Route path="*" element={<Navigate to="login" />} />
        </Routes>
      </AuthorizationLayout>
    </AccountLayout>
  );
}
