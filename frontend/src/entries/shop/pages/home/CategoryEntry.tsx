import { Card, Col, Row } from 'antd';
import { useNavigate } from 'react-router';
import winter from '../../../../assets/winter.jpg';
import {Category, getCategoryText} from '../../../../data';
import { homeEntry } from './style';

const listItems = [
  {
    key: Category.BOOK,
    name: getCategoryText(Category.BOOK),
    url: winter as string,
  },
  {
    key: Category.SPORTS,
    name: getCategoryText(Category.SPORTS),
    url: winter as string,
  },
  {
    key: Category.DAILY,
    name: getCategoryText(Category.DAILY),
    url: winter as string,
  },
  {
    key: Category.CLOTHING,
    name: getCategoryText(Category.CLOTHING),
    url: winter as string,
  },
  {
    key: Category.BEAUTY,
    name: getCategoryText(Category.BEAUTY),
    url: winter as string,
  },
  {
    key: Category.FOOD,
    name: getCategoryText(Category.FOOD),
    url: winter as string,
  },
  {
    key: Category.ELECTRONIC,
    name: getCategoryText(Category.ELECTRONIC),
    url: winter as string,
  },
  {
    key: Category.OTHER,
    name: getCategoryText(Category.OTHER),
    url: winter as string,
  },

];

export const CategoryEntry = () => {
  const navigate = useNavigate();

  return (
    <div className={homeEntry}>
      <Row gutter={[16, 16]}>
        {
          listItems.map((item) =>
            <Col
              xs={12}
              sm={12}
              md={12}
              lg={12}
              xl={6}
              xxl={6}
              key={item.key}
            >
              <Card
                hoverable
                title={item.name}
                onClick={() => navigate(`/c/category/${item.key.toLowerCase()}`)}
              >
                <img src={item.url} alt="entry" className="entryImg" />
              </Card>
            </Col>,
          )}
      </Row>

    </div>
  );
};
