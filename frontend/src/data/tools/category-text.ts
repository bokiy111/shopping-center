import { Category } from '../enums';

export const getCategoryText = (category: Category) => {
  const map = {
    [Category.BOOK]: '书籍教材',
    [Category.SPORTS]: '体育运动',
    [Category.BEAUTY]: '美妆',
    [Category.ELECTRONIC]: '电子产品',
    [Category.DAILY]: '日用百货',
    [Category.CLOTHING]: '服装鞋饰',
    [Category.FOOD]: '饮料食品',
    [Category.OTHER]: '其他',
  };
  return map[category];
};
