import qs from 'qs';
import { transformList } from '../tools/transform-list';
import {GoodsBasicInfoItem, GoodsDetail} from '../../data/entities/goods';
import { IListData, IListRequestQuery } from '../core/types';
import { request } from '../core/http';
import { Category } from '../../data';
import { transformResponse } from '../tools/transform-response';

export interface RequestGoodsByCategoryAndSearchParams extends IListRequestQuery {
  search?: string;
  category?: Category;
}

export async function requestGoodsByCategoryAndSearch(pagingQuery: RequestGoodsByCategoryAndSearchParams){
  const query = qs.stringify(pagingQuery);
  const res = await request.get(`/api/getGoodsByPage?${query}`);
  return transformList<GoodsBasicInfoItem>(transformResponse(res), v => v);
}

export async function requestGoodsDetails(id: string){
  const query = qs.stringify({ id });
  const res = await request.get(`/api/getGoodsBasicDTO?${query}`);
  return transformResponse<GoodsDetail>(res);
}

export interface RequestGoodsBySellerIdParams extends IListRequestQuery {
  sellerId: string;
  search?: string;
}

export async function requestSellerGoodsListByUserId(params: RequestGoodsBySellerIdParams){
  const query = qs.stringify(params);
  const res = await request.get(`/api/seller/getSellerAllGoodsByPageAndSearch?${query}`);
  return transformList<GoodsBasicInfoItem>(transformResponse(res), v => v);
}

export async function requestGoodsCountByUserId(id: string){
  const query = qs.stringify({ id });
  const res = await request.get(`/api/seller/getGoodsCountBySellerId?${query}`);
  return transformResponse<number>(res);
}

export interface RequestAddGoodsData {
  name: string;
  description: string;
  price: number;
  sellerId: string;
  category: Category;
  coverUrl: string;
}

export async function requestAddGoods(data: RequestAddGoodsData){
  const res = await request.post('/api/seller/insert', data);
  return transformResponse(res);
}
