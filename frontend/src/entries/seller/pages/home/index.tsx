import {Avatar, Button, Divider, Flex, Form, Input, message, Modal} from 'antd';
import TextArea from 'antd/es/input/TextArea';
import { useState } from 'react';
import {useSetUser, useUser} from '../../../../models/account/hooks';
import { total } from './style';
import { GoodsList } from './GoodsList';
import { AddGoodsModal, FormValue } from './AddGoodsModal';
import {useRequest} from "ahooks";
import {requestMyInfo, requestUpdateMyInfo} from "../../../../services/requests/user";

export const FALLBACK_AVATAR = 'https://img1.baidu.com/it/u=1672450218,1606872685&fm=253&fmt=auto&app=120&f=JPEG?w=800&h=800';

export interface formValue {
  nickname: string;
  description: string;
  phone: string;
  qq: string;
  wechat: string;
  email: string;
}

export const Home = () => {
  const user = useUser();
  const [form] = Form.useForm<FormValue>();
  const [editInfoModalOpen, setEditInfoModalOpen] = useState(false);
  const setUser = useSetUser();

  const { loading: getInfoLoading, run: getInfo} = useRequest(
    requestMyInfo, {
      onSuccess: (res) => {
        setUser(res);
        message.success('更新成功');
        setEditInfoModalOpen(false);
      },
      onError: (err) => {
        message.error(err.message);
      },
      manual: true,
    }
  )

  const { loading: updateInfoLoading, run: updateInfo } = useRequest(
    requestUpdateMyInfo, {
      manual: true,
      onSuccess: () => {
        getInfo();
      },
      onError: (err) => {
        message.error(err.message);
      },
    });

  return (
    <Flex vertical align="center" gap={30}
      className={total}>
      <h2>Welcome, {user?.nickname}!</h2>
      <Flex justify="center">
        <Flex vertical align="center" style={{ width: 400 }}>
          <Avatar src={user.avatarUrl || FALLBACK_AVATAR} size={100} />
          <div>
            <p>{user.description}</p>
            <p>
              联系方式：
              <ul>
                <li>手机号：{user.phone || '-'}</li>
                <li>QQ：{user.qq || '-'}</li>
                <li>微信：{user.wechat || '-'}</li>
                <li>邮箱：{user.email || '-'}</li>
              </ul>
            </p>
          </div>
          <Button onClick={() => setEditInfoModalOpen(true)}>编辑个人信息</Button>
          <Modal
            title={'编辑个人信息'}
            open={editInfoModalOpen}
            onCancel={() => setEditInfoModalOpen(false)}
            onOk={() => {
              try {
                form.validateFields();
                updateInfo(
                  {
                    ...form.getFieldsValue(),
                    avatarUrl: FALLBACK_AVATAR,
                  }
                )
              } catch (error) {
              }
            }}
            loading={ updateInfoLoading || getInfoLoading}
          >
            <Form
              form={form}
            >
              <Form.Item
                name="nickname"
                required
              >
                <Input placeholder="请输入昵称" onChange={(e) => form.setFieldValue('nickname', e.target.value)} defaultValue={user.nickname}/>
              </Form.Item>
              <Form.Item
                required
                name="description"
              >
                <TextArea placeholder="请输入个人简介"
                  maxLength={200}
                  onChange={(e) => form.setFieldValue('description', e.target.value)}
                  defaultValue={user.description}
                />
              </Form.Item>
              <Form.Item
                name="phone"
              >
                <Input placeholder="请输入手机号" onChange={(e) => form.setFieldValue('phone', e.target.value)}
                       defaultValue={user.phone}

                />

              </Form.Item>
              <Form.Item
                name="qq"
              >
                <Input placeholder="请输入qq号" onChange={(e) => form.setFieldValue('qq', e.target.value)}
                        defaultValue={user.qq}
                />

              </Form.Item>
              <Form.Item
                name="wechat"
              >
                <Input placeholder="请输入微信号" onChange={(e) => form.setFieldValue('wechat', e.target.value)}
                        defaultValue={user.wechat}
                />

              </Form.Item>
              <Form.Item
                name="email"

              >
                <Input placeholder="请输入邮箱" onChange={(e) => form.setFieldValue('email', e.target.value)}
                        defaultValue={user.email}
                />

              </Form.Item>
              <Form.Item
                name="avatarUrl"
              >

              </Form.Item>
            </Form>


          </Modal>
        </Flex>
        <Divider variant="dotted" style={{ borderColor: '#7cb305', height: '100%' }} type="vertical" />
        <GoodsList userId={user.id} />
      </Flex>
    </Flex>
  );
};
