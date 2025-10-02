import { Form, Input, InputNumber, message, Modal, Select } from 'antd';
import { useRequest } from 'ahooks';
import TextArea from 'antd/es/input/TextArea';
import { requestAddGoods } from '../../../../services/requests/goods';
import { Category, getCategoryText } from '../../../../data';
import { UploadPicture } from './UploadPicture';

export interface AddGoodsModalProps {
  open: boolean;
  onClose: () => void;
  userId: string;
  refresh: () => void;
}

export interface FormValue {
  name: string;
  description: string;
  price: number;
  category: Category;
  coverUrl: string;
}

export const AddGoodsModal = ({
  open,
  onClose,
  userId,
  refresh,
}: AddGoodsModalProps) => {
  const [form] = Form.useForm<FormValue>();

  const { loading, run: addGoods } = useRequest(requestAddGoods, {
    onSuccess: () => {
      message.success('添加成功');
      refresh();
      onClose();
    },
    onError: (err) => {
      message.error(err.message);
    },
    manual: true,
  });

  const onOk = () => {

    /**
     *   name: string;
     *   description: string;
     *   price: number;
     *   sellerId: string;
     *   category: Category;
     *   coverUrl: string;
     */
    try {
      form.validateFields();
      addGoods(
        {
          ...form.getFieldsValue(),
          sellerId: userId,
        },
      );
    } catch (e) { /* empty */ }
  };

  return (
    <Modal
      title="添加商品"
      onCancel={onClose}
      open={open}
      onOk={onOk}
      loading={loading}
    >
      <Form
        form={form}
      >
        <Form.Item
          name="name"
          validateTrigger={['onBlur']}
          rules={[
            {
              required: true,
              message: '请输入商品名称！',
            },
          ]}
        >
          <Input
            placeholder="请输入商品名称"
            onChange={(e) => form.setFieldValue('name', e.target.value)}
          />
        </Form.Item>
        <Form.Item
          name="price"
          validateTrigger={['onBlur']}
          rules={[
            {
              required: true,
              message: '请输入商品价格！',
            },
          ]}
        >
          <InputNumber
            addonBefore="￥"
            addonAfter="元"
            placeholder="请输入商品价格"
            onChange={(value) => form.setFieldValue('price', value)}
          />
        </Form.Item>
        <Form.Item
          name="category"
          validateTrigger={['onBlur']}
          rules={[
            {
              required: true,
              message: '请选择商品分类！',
            },
          ]}
        >
          <Select
            placeholder="请选择商品分类"
            onChange={(value) => form.setFieldValue('category', value)}
            options={[
              { value: Category.BOOK, label: getCategoryText(Category.BOOK) },
              { value: Category.OTHER, label: getCategoryText(Category.OTHER) },
              { value: Category.BEAUTY, label: getCategoryText(Category.BEAUTY) },
              { value: Category.ELECTRONIC, label: getCategoryText(Category.ELECTRONIC) },
              { value: Category.DAILY, label: getCategoryText(Category.DAILY) },
              { value: Category.SPORTS, label: getCategoryText(Category.SPORTS) },
              { value: Category.FOOD, label: getCategoryText(Category.FOOD) },
              { value: Category.CLOTHING, label: getCategoryText(Category.CLOTHING) },
            ]}
          />
        </Form.Item>
        <Form.Item
          name="description"
          validateTrigger={['onBlur']}
          rules={[
            {
              required: true,
              message: '请输入商品描述！',
            },
          ]}
        >
          <TextArea
            showCount
            maxLength={200}
            placeholder="请输入商品描述"
            autoSize={{ minRows: 3, maxRows: 6 }}
            onChange={(e) => form.setFieldValue('description', e.target.value)}
          />
        </Form.Item>
        <Form.Item
          name="coverUrl"
          extra="目前只展示上传的第一张图片，后续会支持多图上传"
          validateTrigger={['onBlur']}
          rules={[
            {
              required: true,
              message: '请上传商品图片！',
            },
          ]}
        >
          <UploadPicture form={form} />
        </Form.Item>
      </Form>
    </Modal>
  );
};
