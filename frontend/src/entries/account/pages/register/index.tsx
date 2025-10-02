import { Button, Form, Input, message } from 'antd';
import { useRequest } from 'ahooks';
import { LockOutlined, UserOutlined } from '@ant-design/icons';
import { useNavigate } from 'react-router';
import { requestRegister } from '../../../../services/requests/account';

export interface RegisterFormData {
  account: string;
  password: string;
}

export const Register = () => {
  const navigate = useNavigate();

  const { loading, run: register } = useRequest(requestRegister, {
    onSuccess: () => {
      message.success('注册成功');
      navigate('/u/login');
    },
    onError: (error) => {
      message.error(error.message);
    },
    manual: true,
  });

  const onFinish = (values: RegisterFormData) => {
    register(values);
  };

  return (
    <Form
      name="login"
      style={{ maxWidth: 280, width: '100%' }}
      onFinish={onFinish}
    >
      <Form.Item
        name="account"
        rules={[{ required: true, message: '请输入账号！' }]}
      >
        <Input prefix={<UserOutlined />} size="large" placeholder="请输入账号" />
      </Form.Item>
      <Form.Item
        name="password"
        rules={[{ required: true, message: '请输入密码！' }]}
      >
        <Input prefix={<LockOutlined />} type="password" size="large" placeholder="请输入密码" />
      </Form.Item>
      <Form.Item
        name="repassword"
        dependencies={['password']}
        validateTrigger={['onBlur']}
        rules={[
          {
            required: true,
            message: '请再次输入密码！',
          },
          ({ getFieldValue }) => ({
            validator(_, value) {
              if (!value || getFieldValue('password') === value) {
                return Promise.resolve();
              }
              return Promise.reject(new Error('两次输入密码不一致！'));
            },
          }),
        ]}
      >
        <Input prefix={<LockOutlined />} type="password" size="large" placeholder="请再次输入密码" />
      </Form.Item>
      <Form.Item>
        <Button
          block
          type="primary"
          htmlType="submit"
          loading={loading}
          size="large"
        >
          注册
        </Button>
        <a onClick={() => navigate('/u/login')}>返回登录</a>
      </Form.Item>
    </Form>
  );
};
