import debug from './debug';

export interface LoggerParams {
  content: any;
  key: string;
  category?: string;
}

// TODO 后续实现日志主动上报
export const logger = {
  info(params: LoggerParams) {
    debug.info(params);
  },
  error(params: LoggerParams) {
    debug.error(params);
  },
};
