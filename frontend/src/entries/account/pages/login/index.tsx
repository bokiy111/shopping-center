import { Button, Form, Input, message } from 'antd';
import { useRequest } from 'ahooks';
import { LockOutlined, UserOutlined } from '@ant-design/icons';
import { useNavigate } from 'react-router';
import { requestLogin } from '../../../../services/requests/account';
import { useSetUser } from '../../../../models/account/hooks';
import { AutoLogin } from '../../../../authorization';

export interface LoginFormData {
  account: string;
  password: string;
}

export const Login = () => {
  const setUser = useSetUser();
  const navigate = useNavigate();

  const { loading, run: login } = useRequest(requestLogin, {
    onSuccess: (data) => {
      message.success('登录成功');
      setUser(data);
      navigate('/s/home');
    },
    onError: (error) => {
      message.error(error.message);
    },
    manual: true,
  });

  const onFinish = (values: LoginFormData) => {
    login(values);
  };

  return (
    <AutoLogin>
      <Form
        name="login"
        style={{ maxWidth: 280, width: '100%' }}
        onFinish={onFinish}
      >
        <Form.Item
          name="account"
          rules={[{ required: true, message: '请输入账号！' }]}
        >
          <Input prefix={<UserOutlined />} placeholder="请输入账号" size="large" />
        </Form.Item>
        <Form.Item
          name="password"
          rules={[{ required: true, message: '请输入密码！' }]}
        >
          <Input prefix={<LockOutlined />} type="password" size="large"
            placeholder="请输入密码" />
        </Form.Item>
        <Form.Item>
          <Button
            block
            type="primary"
            htmlType="submit"
            loading={loading}
            size="large"
          >
            登录
          </Button>
          <a onClick={() => navigate('/u/register')}>立即注册</a>
        </Form.Item>
      </Form>
    </AutoLogin>
  );
};
