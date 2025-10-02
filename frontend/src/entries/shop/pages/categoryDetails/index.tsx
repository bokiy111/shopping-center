import { useParams } from 'react-router';
import {Divider, Flex, message, Pagination, Spin} from 'antd';
import { usePagination } from 'ahooks';
import { Params } from 'ahooks/es/usePagination/types';
import { Category, getCategoryText } from '../../../../data';
import { requestGoodsByCategoryAndSearch } from '../../../../services/requests/goods';
import { CardList } from './CardList';

export const CategoryDetails = () => {
  const { categoryParam } = useParams();
  const category = Category[categoryParam?.toUpperCase() as keyof typeof Category];

  const { data, loading, pagination } = usePagination(
    // <TData extends Data, TParams extends Params> = (...args: TParams) => Promise<TData>
    (...args: Params) => {
      const [params] = args;
      const { current, pageSize } = params;
      return requestGoodsByCategoryAndSearch(
        {
          current: current,
          pageSize: pageSize,
          category: category,
          search: '',
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

  return (
    <Flex
      vertical
      align="center"
      style={{ width: '100%' }}
      gap={20}
    >
      <div>
        <h1>{getCategoryText(category)}</h1>
        <Divider />
      </div>
      {
        loading ? <Spin spinning /> : <>
          <CardList data={data?.list} />
          <Pagination
            showQuickJumper
            defaultCurrent={1}
            current={pagination.current}
            pageSize={pagination.pageSize}
            total={data?.total}
            onChange={pagination.onChange}
          />
        </>
      }

    </Flex>
  );
};
