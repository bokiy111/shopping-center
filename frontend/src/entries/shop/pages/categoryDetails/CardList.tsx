import {Card, Col, Image, Row} from 'antd';
import { GoodsBasicInfoItem } from '../../../../data/entities/goods';
import {goodsImage} from "./style";
import {useState} from "react";
import {GoodsDetailsModal} from "./GoodsDetailsModal";

export interface CardListProps {
  data: GoodsBasicInfoItem[] | undefined;
}

export const FALLBACK_COVER = 'https://th.bing.com/th/id/OIP.D-R0h465OeWKQ23zNELQ6gHaLG?w=120&h=180&c=7&r=0&o=5&dpr=2.5&pid=1.7';
export const CardList = ({
  data,
}:CardListProps) => {
  const [modalOpen, setModalOpen] = useState(false);
  const [goodsId, setGoodsId] = useState<string>('');

  return (
    <div>
      <GoodsDetailsModal
        goodsId={goodsId}
        open={modalOpen}
        setOpen={setModalOpen}
      />
      <Row gutter={[32, 32]}>
        {
          data?.map((item) =>
            <Col
              xs={12}
              sm={8}
              md={8}
              lg={8}
              xl={6}
              xxl={6}
              key={item.id}
            >
              <Card
                hoverable
                cover={
                  <img
                    // src={item.coverUrl || FALLBACK_COVER}
                    src={FALLBACK_COVER}
                    alt="cover"
                    className={goodsImage}
                  />
                }
                onClick={() => {
                  setGoodsId(item.id);
                  setModalOpen(true);
                }}
              >
                <div>{item.name}</div>
                <div>{item.basicDescription}</div>
                <div>{item.price}</div>
              </Card>
            </Col>,
          )
        }
      </Row>
    </div>

  );
};
