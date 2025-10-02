import { Avatar, Card, Carousel, Divider, List, message, Modal, Spin, Typography } from 'antd';
import { useRequest } from 'ahooks';
import { useState } from 'react';
import { requestGoodsDetails } from '../../../../services/requests/goods';
import {goodsDetailsModal, goodsDetailsWrapper} from './style';
import { FALLBACK_COVER } from './CardList';


export interface GoodsDetailsModalProps {
  open: boolean;
  setOpen: (open: boolean) => void;
  goodsId: string;
}

export interface Contact {
  key: string;
  value: string;
}

export const GoodsDetailsModal = ({
  goodsId,
  open,
  setOpen,
}: GoodsDetailsModalProps) => {
  const [contacts, setContacts] = useState<Contact[]>([]);

  const { data, loading } = useRequest(requestGoodsDetails, {
    onSuccess: (res) => {
      setContacts([
        {
          key: '邮箱',
          value: res.seller.email,
        },
        {
          key: 'QQ',
          value: res.seller.qq,
        },
        {
          key: '电话',
          value: res.seller.phone,
        },
        {
          key: '微信',
          value: res.seller.wechat,
        },
      ]);
    },
    onError: (err) => {
      message.error(err.message);
    },
    defaultParams: [goodsId],
    ready: open,
  });

  return (
    <Modal
      open={open}
      onCancel={() => setOpen(false)}
      centered
      width={1000}
      footer={null}
    >
      <div className={goodsDetailsWrapper}>
        {
          loading ? <Spin spinning={loading} /> : (
            <>
              <div className="left">
                <Carousel
                  className="carousel"
                  arrows={true}
                >
                  <img src={FALLBACK_COVER} alt="details" className="goodsImage" />
                </Carousel>
              </div>
              <div className="right">
                <div className="goods">
                  <div>
                    <h3>商品信息</h3>
                  </div>
                  <div className="info">
                    <div>{data?.name}</div>
                    <div>{data?.price}</div>
                    <div>{data?.description}</div>
                  </div>
                </div>
                <Divider />
                <div className="seller">
                  <h3>卖家信息</h3>
                  <div className="info">
                    <div>
                      <Avatar src={data?.seller.avatarUrl} />
                      <div>{data?.seller.nickname}</div>
                      <div>{data?.seller.description}</div>
                    </div>
                    <List
                      dataSource={contacts}
                      renderItem={(contacts) => (
                        <List.Item>
                          <Typography.Text strong>{contacts.key}</Typography.Text>
                          <Typography.Text mark>{contacts.value}</Typography.Text>
                        </List.Item>
                      )}
                    />
                  </div>
                </div>
              </div>
            </>
          )
        }
      </div>
    </Modal>
  );
};
