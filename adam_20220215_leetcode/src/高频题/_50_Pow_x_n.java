package 高频题;

/** https://leetcode-cn.com/problems/powx-n/
 * @auther adam
 * @date 2022/2/21
 * @apiNote 高频题
 */
public class _50_Pow_x_n {
    /// 非递归
    public double myPow2(double x, int n) {
        boolean neg = n < 0;
        long y = neg ? -((long)n) : n;
        double res = 1.0;
        while (y > 0) {
            if ((y & 1) == 1) {
                /// 如果最后一个二进制为是1就累乘上x
                res *= x;
            }
            x *= x;
            // 舍弃掉最后一位二进制位
            y >>= 1;
        }
        return neg ? (1 / res) : res;
    }
    /// 递归
    public double myPow(double x, int n) {
        if (n == 0) return 1;
        if (n == -1) return 1 / x;
        // 是否为基数
        boolean odd = (n & 1) == 1;
        double half = myPow(x, n >> 1);
        half *= half;
        /// 是负数
//        x = n < 0 ? (1 / x) : x;
        return odd ? (half * x) : half;
    }

    public static void main(String[] args) {
        _50_Pow_x_n o = new _50_Pow_x_n();
        System.out.println(o.myPow2(2, 10));

        System.out.println(o.myPow2(2, -2));
    }
}
