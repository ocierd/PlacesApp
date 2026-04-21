export interface LoginData {
    username: string;
    password: string;
};

export interface TokenData{
    token: string;
    expiresIn: number;
    refreshToken: string;
    refreshTokenExpiresIn: number;
}