import { Category, GoodsStatus } from '../enums';
import { GoodsDetailsUser } from './user';

export interface Goods {
  id: string;
  name: string;
  price: number;
  coverUrl: string;
  description: number;
  sellerId: string;
  createTime: string;
  updateTime: string;
  status: GoodsStatus;
  category: Category;
}

export interface GoodsBasicInfoItem extends Pick<Goods, 'id' | 'name' | 'price' | 'coverUrl'> {
  basicDescription: string;
}

export interface GoodsDetail extends
  Pick< Goods,
  'id' |
  'name' | 'price' | 'coverUrl' | 'description' | 'createTime' | 'updateTime' | 'status' | 'category'>
  // Pick<User, 'id' | 'nickname' | 'avatar_url' | 'phone' | 'qq' | 'email' | 'wechat' | 'description'>
{
  seller: GoodsDetailsUser;
}
