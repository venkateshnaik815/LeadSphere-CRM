// @ts-nocheck
import * as v from 'valibot';

export const UserResponseSchema = v.pipe(
  v.object({
    id: v.number(),
    firstName: v.string(),
    lastName: v.string(),
    role: v.object({
      id: v.number(),
      name: v.string(),
    }),
  }),
  v.transform(input => ({
    id: input.id,
    firstName: input.firstName,
    lastName: input.lastName,
    fullName: `${input.firstName} ${input.lastName}`,
    role: input.role.name,
  })),
);

export type UserResponse = v.InferOutput<typeof UserResponseSchema>;
