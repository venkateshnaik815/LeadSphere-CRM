// @ts-nocheck
export interface MulterOptions {
  dest?: string | Function;
  storage?: any;
  limits?: {
    fieldNameSize?: number;
    fieldSize?: number;
    fields?: number;
    fileSize?: number;
    files?: number;
    parts?: number;
    headerPairs?: number;
  };

  preservePath?: boolean;

  defParamCharset?: string;

  fileFilter?(
    req: any,
    file: {
      fieldname: string;
      originalname: string;
      encoding: string;
      mimetype: string;
      size: number;
      destination: string;
      filename: string;
      path: string;
      buffer: Buffer;
    },
    callback: (error: Error | null, acceptFile: boolean) => void,
  ): void;
}

export interface MulterField {
  name: string;
  maxCount?: number;
}
