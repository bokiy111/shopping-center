import { GoodsStatus } from '../enums';

/**
 * 书籍教材、体育运动、美妆、电子产品、日用百货、服装鞋饰、饮料食品、其他
 */
export const getGoodsStatusText = (status: GoodsStatus) => {
  const map = {
    [GoodsStatus.ONSALE]: '售卖中',
    [GoodsStatus.ONSALE]: '已下架',
  };
  return map[status];
};
