## Solution

将字符串的0和1数出来之后，转化为一个有二维限制的0-1背包问题。

**状态**: dp[i][j][k]表示当0和1的限制分别为i和j时，前k个字符串中可取的最大子集数。
**状态转移方程**:
```
dp[i][j][k] = dp[i][j][k-1]                   # 如果不取第k个字符串
            = 1 + dp[i-count0][j-count1][k-1] # 如果取第k个字符串（满足0和1的限制）
```
