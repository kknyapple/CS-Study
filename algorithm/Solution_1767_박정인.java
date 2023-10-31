package swea;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV4suNtaXFEDFAUf
 * 
 * @author SSAFY
 *
 */
public class Solution_1767_박정인 {
	static class Position {
		int x, y;

		public Position(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
	}

	static int N, map[][];
	static int result, maxCoreCnt;
	static List<Position> cores;

	static int[] dx = { -1, 0, 1, 0 };
	static int[] dy = { 0, -1, 0, 1 };

	static final int BLANK = 0;
	static final int CORE_LINE = 1;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;

		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());
			map = new int[N][N];
			cores = new ArrayList<>();

			result = Integer.MAX_VALUE;
			maxCoreCnt = Integer.MIN_VALUE;

			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());

					// 전원과 닿지 않은 코어 위치 저장
					if (map[i][j] == CORE_LINE) {
						if (i != 0 && i != N - 1 && j != 0 && j != N - 1) {
							cores.add(new Position(i, j));
						}
					}
				}
			}

			dfs(0, 0, 0);
			sb.append("#").append(tc).append(" ").append(result).append("\n");
		}

		System.out.println(sb);
	}

	// 매번 전선을 확인하고 결과에 반영
	// 전선 겹치는 경우 어차피 각 방향의 경우를 모두 체크하기 때문에 | 방향 -방향으로 겹칠 경우 | 먼저 설치한 경우, - 먼저 설치한 경우
	// 다 체크가 된다.
	private static void dfs(int index, int coreCnt, int lineLen) { // 현재 확인할 코어 index, 코어 개수, 전선 길이
		if (index == cores.size()) {
			if (maxCoreCnt == coreCnt) {
				result = Math.min(result, lineLen);
			} else if (maxCoreCnt < coreCnt) {
				maxCoreCnt = coreCnt;
				result = lineLen;
			}
			return;
		}

		int x = cores.get(index).x;
		int y = cores.get(index).y;

		// 방향 인덱스
		for (int d = 0; d < 4; d++) {
			int nx = x;
			int ny = y;
			int cnt = 0; // 전선 길이
			boolean isDuplicated = false; // 전선이 겹치는지 여부

			// 전선 길이를 세고 겹치는지 확인
			while (true) {
				nx += dx[d];
				ny += dy[d];

				if (!isRange(nx, ny)) {
					break;
				}

				// 다음 칸이 빈칸이 아닌 경우 >> 전선, 코어가 존재
				if (map[nx][ny] != BLANK) {
					isDuplicated = true;
					break;
				}

				cnt++;
			}

			if (isDuplicated) { // 겹치는 경우 >> 코어개수, 전선 길이 반영X
				dfs(index + 1, coreCnt, lineLen);
			} else { // 겸치지 않는 경우
				// map에 전선 반영
				nx = x;
				ny = y;
				for (int i = 0; i < cnt; i++) {
					nx += dx[d];
					ny += dy[d];

					map[nx][ny] = CORE_LINE;
				}

				// 코어수 1 증가, 전선 길이 증가
				dfs(index + 1, coreCnt + 1, lineLen + cnt);
			}

			if (!isDuplicated) { // 원상복구
				nx = x;
				ny = y;
				for (int i = 0; i < cnt; i++) {
					nx += dx[d];
					ny += dy[d];

					map[nx][ny] = BLANK;
				}
			}
		}
	}

	private static boolean isRange(int x, int y) {
		return x >= 0 && x < N && y >= 0 && y < N;
	}
}
