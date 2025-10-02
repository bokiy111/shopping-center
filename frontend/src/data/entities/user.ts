import { UserRole } from '../enums';

export interface User {
  id: string;
  account: string;
  nickname: string;
  avatarUrl: string;
  role: UserRole;
  wechat: string;
  phone: string;
  qq: string;
  email: string;
  description: string;
  createTime: string;
  updateTime: string;
}

export interface GoodsDetailsUser extends
  Pick<User, 'id' | 'nickname' | 'avatarUrl' | 'phone' | 'qq' | 'email' | 'wechat' | 'description'> {
}
