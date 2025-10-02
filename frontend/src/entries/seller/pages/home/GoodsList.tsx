import {Button, Card, Col, Flex, message, Pagination, Row, Spin, Typography} from 'antd';
import { usePagination, useRequest } from 'ahooks';
import { Params } from 'ahooks/es/usePagination/types';
import Search from 'antd/es/input/Search';
import { useState } from 'react';
import { search } from '../../../../components/BaseLayout/styles';
import {
  requestGoodsCountByUserId,
  requestSellerGoodsListByUserId,
} from '../../../../services/requests/goods';
import { Bootstrap } from '../../../../boostrap';
import { AddGoodsModal } from './AddGoodsModal';
import dayjs from "dayjs";
import {GoodsDetailsModal} from "../../../shop/pages/categoryDetails/GoodsDetailsModal";
import {sellHomeGoodsCard} from "./style";

export interface GoodsListProps {
  userId: string;
}

export const GoodsList = ({
  userId,
}: GoodsListProps) => {
  const [search, setSearch] = useState<string>('');
  const [goodsCount, setGoodsCount] = useState<number>(0);
  const [openAddGoodsModal, setOpenAddGoodsModal] = useState<boolean>(false);

  const [modalOpen, setModalOpen] = useState(false);
  const [goodsId, setGoodsId] = useState<string>('');

  const { loading: getCountLoading } = useRequest(
    requestGoodsCountByUserId, {
      defaultParams: [userId],
      onSuccess: (data) => {
        setGoodsCount(data);
      },
      onError: (err) => {
        message.error(err.message);
      },
    },
  );

  const { data, loading, pagination, refresh } = usePagination(
    // <TData extends Data, TParams extends Params> = (...args: TParams) => Promise<TData>
    (...args: Params) => {
      const [params] = args;
      const { current, pageSize } = params;
      return requestSellerGoodsListByUserId(
        {
          current: current,
          pageSize: pageSize,
          sellerId: userId,
          search: search,
        },
      );
    },
    {
      onError: (err) => {
        message.error(err.message);
      },
      defaultPageSize: 12,
    },
  );

  const goodsList = (
    <>
      <GoodsDetailsModal
        goodsId={goodsId}
        open={modalOpen}
        setOpen={setModalOpen}
      />
      <Row gutter={[16, 16]}>
        {
          data?.list.map((item) => (
            <Col
              key={item.id}
            >
              <Flex
                className={sellHomeGoodsCard}
                style={{ width: 300, height: 150, padding: 15, margin: 10, background: '#fff', borderRadius: 10 }}
                justify="start"
                gap={10}
                onClick={() => {
                  setGoodsId(item.id);
                  setModalOpen(true);
                }}
              >
                <img alt="example" src={item.coverUrl}
                  style={{ width: 120, height: 120, objectFit: 'cover', borderRadius: 10 }}
                />
                <Flex
                  vertical
                  align="start"
                  justify="space-between"
                >
                  <div>
                    <h3 style={{margin: 0}}>{item.name}</h3>
                    <strong style={{ color: '#26f5e2' }}>￥ {item.price} 元</strong>
                  </div>
                  <strong>{item.price}</strong>
                </Flex>

              </Flex>
            </Col>
          ))
        }
      </Row>

      <Pagination
        showQuickJumper
        defaultCurrent={1}
        current={pagination.current}
        pageSize={pagination.pageSize}
        total={data?.total}
        onChange={pagination.onChange}
      />
    </>
  );

  const noGoodsAtAllTip =
    <span>您暂未添加任何商品</span>
  ;

  return (
    <Flex vertical align="center" style={{ width: '100%', maxWidth: 800 }}>
      <AddGoodsModal
        open={openAddGoodsModal}
        onClose={() => setOpenAddGoodsModal(false)}
        userId={userId}
        refresh={refresh}
      />
      <Flex gap={5} style={{ width: '100%' }}>
        <Button onClick={() => setOpenAddGoodsModal(true)}>添加物品</Button>
        <Search
          placeholder="搜索您的商品"
          style={{ width: '100%' }}
          onChange={(e) => setSearch(e.target.value)}
          onSearch={refresh}
          loading={!!search && loading}
        />
      </Flex>
      {
        getCountLoading ?
          <Spin spinning />
          :
          goodsCount === 0 ?
            noGoodsAtAllTip
            :
            data?.list.length === 0 ? noGoodsAtAllTip : goodsList
      }
    </Flex>
  );
};
