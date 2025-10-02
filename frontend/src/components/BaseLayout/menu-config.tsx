import {
  AppstoreOutlined,
  ContainerOutlined,
  ControlOutlined,
  IdcardOutlined,
  ProfileOutlined,
  ProjectOutlined,
  TeamOutlined,
} from '@ant-design/icons';
import { MenuProps } from 'antd';
import { ArrayItemType } from '../../utils/type-utils';

type ItemType = ArrayItemType<Exclude<MenuProps['items'], undefined>>;

const workspace: ItemType = {
  key: 'workspace',
  icon: <AppstoreOutlined />,
  label: '工作台',
};

const myTeam: ItemType = {
  key: 'teams',
  icon: <TeamOutlined />,
  label: '我的团队',
};

const myProject: ItemType = {
  key: 'projects',
  icon: <ProjectOutlined />,
  label: '我的项目',
};

const activityManage: ItemType = {
  key: 'activities',
  icon: <ProfileOutlined />,
  label: '参赛项目管理',
};

// const onlineCompetition: ItemType = {
//   key: 'online-competition',
//   icon: <GlobalOutlined />,
//   label: '网评赛事',
// };
//
// const siteCompetition: ItemType = {
//   key: 'site-competition',
//   icon: <TrophyOutlined />,
//   label: '现场赛事',
// };

const reviewManage: ItemType = {
  key: 'reviews',
  icon: <ControlOutlined />,
  label: '评审管理',
};

const accountManage: ItemType = {
  key: 'accounts',
  icon: <IdcardOutlined />,
  label: '账号管理',
};

const contentManage: ItemType = {
  key: 'contents',
  icon: <ContainerOutlined />,
  label: '内容管理',
};

const teamManage: ItemType = {
  key: 'teams',
  icon: <TeamOutlined />,
  label: '团队信息',
};

// const enterpriseManage: ItemType = {
//   key: 'enterprises',
//   icon: <ApartmentOutlined />,
//   label: '企业管理',
// };

// const projectManage: ItemType = {
//   key: 'projects',
//   icon: <ProjectOutlined />,
//   label: '项目管理',
// };

const withChildren = (item: ItemType, children: ItemType[]): ItemType => {
  return {
    ...item,
    children,
  } as ItemType;
};

export const StudentMenuConfig: MenuProps['items'] = [
  workspace,
  myTeam,
  // withChildren(myProject, [
  //   {
  //     key: 'projects/list',
  //     label: '项目列表',
  //   },
  // ]),
  withChildren(activityManage, [
    {
      key: 'activities/list',
      label: '赛区选择',
    },
    {
      key: 'activities/application-records',
      label: '审核记录',
    },
    // {
    //   key: 'activities/sign-records',
    //   label: '签到记录',
    // },
  ]),
  // onlineCompetition,
  // siteCompetition,
];

export const TeacherMenuConfig: MenuProps['items'] = [
  workspace,
  myTeam,
  myProject,
  activityManage,
  // onlineCompetition,
  // siteCompetition,
  reviewManage,
];

export const AdminMenuConfig: MenuProps['items'] = [
  workspace,
  withChildren(accountManage, [
    {
      key: 'accounts/managers',
      label: '管理员账号',
    },
    {
      key: 'accounts/users',
      label: '用户账号',
    },
    // {
    //   key: 'accounts/investors',
    //   label: '投资人账号',
    // },
    // {
    //   key: 'accounts/review',
    //   label: '账号审核',
    // },
  ]),
  withChildren(contentManage, [
    {
      key: 'contents/labels',
      label: '标签管理',
    },
  ]),
  withChildren(teamManage, [
    {
      key: 'teams/list',
      label: '团队列表',
    },
  ]),
  // withChildren(enterpriseManage, [
  //   {
  //     key: 'enterprises/list',
  //     label: '企业列表',
  //   },
  //   {
  //     key: 'enterprises/applications',
  //     label: '企业申请',
  //   },
  //   {
  //     key: 'enterprises/create',
  //     label: '新建企业',
  //   },
  // ]),
  // withChildren(projectManage, [
  //   {
  //     key: 'projects/list',
  //     label: '项目列表',
  //   },
  // ]),
  withChildren(activityManage, [
    {
      key: 'activities/list',
      label: '赛事列表',
    },
    {
      key: 'activities/create',
      label: '创建赛事',
    },
    {
      key: 'activities/handle-stuff',
      label: '待处理列表',
    },
  ]),
  // onlineCompetition,
  // siteCompetition,
];

export const ContestManagerMenuConfig: MenuProps['items'] = [
  {
    key: 'activities/list',
    label: '赛事列表',
  },
];
