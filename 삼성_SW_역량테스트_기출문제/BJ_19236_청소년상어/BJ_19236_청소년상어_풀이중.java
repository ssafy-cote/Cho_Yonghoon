package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ_19236_청소년상어 {
	static Fish[] list;
	static int max;
	static Shark shark;

	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;

		Fish[][] map = new Fish[4][4];

		list = new Fish[17];
		for (int i = 0; i < 4; i++) {
			st = new StringTokenizer(bf.readLine(), " ");
			for (int j = 0; j < 4; j++) {
				int num = Integer.parseInt(st.nextToken());
				int dir = Integer.parseInt(st.nextToken());
				list[num] = new Fish(num, dir, i, j);
				map[i][j] = new Fish(num, dir, i, j);
			}
		}
		shark = new Shark(map[0][0].dir, 0, 0);
		int sum = map[0][0].num;
		int x = map[0][0].x;
		int y = map[0][0].y;
		int dir = map[0][0].dir;
		list[map[0][0].num] = null;
		map[0][0] = null;

		max = 0;
		fishMove(map);
//		for(int i = 0; i < 4; i ++) {
//			for(int j = 0; j < 4; j++) {
//				if(map[i][j] == null) {
//					System.out.print(0 + " ");
//				}else {
//					System.out.print(map[i][j].num + " ");
//				}
//			}
//			System.out.println();
//		}
//		System.out.println(shark.dir+" " + shark.x + " " +shark.y + temp);
//		System.out.println("=======================================");
		sharkMove(sum, x, y, dir, 1, map);

		System.out.println(max);
	}

	static Fish[][] fishMove(Fish[][] map) {
		for (int i = 1; i < list.length; i++) {
			if (list[i] == null)
				continue;
			int x = list[i].x;
			int y = list[i].y;
			int cnt = 0;
			while (cnt < 8) {
				if (list[i].dir == 1) { // 위
					if ((x - 1 > -1) && SharkCheck(x - 1, y)) {
						if (map[x - 1][y] == null) {
							map[x - 1][y] = map[x][y];
							map[x - 1][y].setPos(x - 1, y);
							list[i].setPos(x - 1, y);
							map[x][y] = null;
							break;
						} else {
							Fish temp = map[x][y];
							map[x][y] = map[x - 1][y];
							map[x - 1][y] = temp;
							map[x][y].setPos(x, y);
							map[x - 1][y].setPos(x - 1, y);
							list[i].setPos(x - 1, y);
							list[map[x][y].num].setPos(x, y);
							break;
						}
					} else {
						list[i].dir = 2;
						map[x][y].dir = 2;
						cnt++;
					}
				}
				if (list[i].dir == 2) {
					if ((x - 1 > -1) && (y - 1 > -1) && SharkCheck(x - 1, y - 1)) {

						if (map[x - 1][y - 1] == null) {
							map[x - 1][y - 1] = map[x][y];
							map[x - 1][y - 1].setPos(x - 1, y - 1);
							list[i].setPos(x - 1, y - 1);
							map[x][y] = null;
							break;
						} else {
							Fish temp = map[x][y];
							map[x][y] = map[x - 1][y - 1];
							map[x - 1][y - 1] = temp;

							map[x][y].setPos(x, y);
							map[x - 1][y - 1].setPos(x - 1, y - 1);
							list[i].setPos(x - 1, y - 1);
							list[map[x][y].num].setPos(x, y);

							break;
						}
					} else {
						list[i].dir = 3;
						map[x][y].dir = 3;
						cnt++;
					}
				}
				if (list[i].dir == 3) {
					if (y - 1 > -1 && SharkCheck(x, y - 1)) {

						if (map[x][y - 1] == null) {
							map[x][y - 1] = map[x][y];
							map[x][y - 1].setPos(x, y - 1);
							list[i].setPos(x, y - 1);
							map[x][y] = null;
							break;
						} else {
							Fish temp = map[x][y];
							map[x][y] = map[x][y - 1];
							map[x][y - 1] = temp;
							map[x][y].setPos(x, y);
							map[x][y - 1].setPos(x, y - 1);
							list[i].setPos(x, y - 1);
							list[map[x][y].num].setPos(x, y);

							break;
						}
					} else {
						list[i].dir = 4;
						map[x][y].dir = 4;
						cnt++;
					}
				}
				if (list[i].dir == 4) {
					if ((x + 1 < 4) && (y - 1 > -1) && SharkCheck(x + 1, y - 1)) {

						if (map[x + 1][y - 1] == null) {
							map[x + 1][y - 1] = map[x][y];
							map[x + 1][y - 1].setPos(x + 1, y - 1);
							list[i].setPos(x + 1, y - 1);
							map[x][y] = null;
							break;
						} else {
							Fish temp = map[x][y];
							map[x][y] = map[x + 1][y - 1];
							map[x + 1][y - 1] = temp;

							map[x][y].setPos(x, y);
							map[x + 1][y - 1].setPos(x + 1, y - 1);
							list[i].setPos(x + 1, y - 1);
							list[map[x][y].num].setPos(x, y);

							break;
						}

					} else {
						list[i].dir = 5;
						map[x][y].dir = 5;
						cnt++;
					}

				}
				if (list[i].dir == 5) { // 아래
					if (x + 1 < 4 && SharkCheck(x + 1, y)) {
						if (map[x + 1][y] == null) {
							map[x + 1][y] = map[x][y];
							map[x + 1][y].setPos(x + 1, y);
							list[i].setPos(x + 1, y);
							map[x][y] = null;
							break;
						} else {
							Fish temp = map[x][y];
							map[x][y] = map[x + 1][y];
							map[x + 1][y] = temp;

							map[x][y].setPos(x, y);
							map[x + 1][y].setPos(x + 1, y);
							list[i].setPos(x + 1, y);
							list[map[x][y].num].setPos(x, y);

							break;
						}
					} else {
						list[i].dir = 6;
						map[x][y].dir = 6;
						cnt++;
					}
				}
				if (list[i].dir == 6) {
					if ((x + 1 < 4) && (y + 1 < 4) && SharkCheck(x + 1, y + 1)) {
						if (map[x + 1][y + 1] == null) {
							map[x + 1][y + 1] = map[x][y];
							map[x + 1][y + 1].setPos(x + 1, y + 1);
							list[i].setPos(x + 1, y + 1);
							map[x][y] = null;
							break;
						} else {
							Fish temp = map[x][y];
							map[x][y] = map[x + 1][y + 1];
							map[x + 1][y + 1] = temp;

							map[x][y].setPos(x, y);
							map[x + 1][y + 1].setPos(x + 1, y + 1);
							list[i].setPos(x + 1, y + 1);
							list[map[x][y].num].setPos(x, y);

							break;
						}
					} else {
						list[i].dir = 7;
						map[x][y].dir = 7;
						cnt++;
					}

				}
				if (list[i].dir == 7) {
					if (y + 1 < 4 && SharkCheck(x, y + 1)) {
						if (map[x][y + 1] == null) {
							map[x][y + 1] = map[x][y];
							map[x][y + 1].setPos(x, y + 1);
							list[i].setPos(x, y + 1);
							map[x][y] = null;
							break;
						} else {
							Fish temp = map[x][y];
							map[x][y] = map[x][y + 1];
							map[x][y + 1] = temp;

							map[x][y].setPos(x, y);
							map[x][y + 1].setPos(x, y + 1);
							list[i].setPos(x, y + 1);
							list[map[x][y].num].setPos(x, y);

							break;
						}
					} else {
						list[i].dir = 8;
						map[x][y].dir = 8;
						cnt++;
					}
				}
				if (list[i].dir == 8) {
					if ((x - 1 > -1) && (y + 1 < 4) && SharkCheck(x - 1, y + 1)) {
						if (map[x - 1][y + 1] == null) {
							map[x - 1][y + 1] = map[x][y];
							map[x - 1][y + 1].setPos(x - 1, y + 1);
							list[i].setPos(x - 1, y + 1);
							map[x][y] = null;
							break;
						} else {
							Fish temp = map[x][y];
							map[x][y] = map[x - 1][y + 1];
							map[x - 1][y + 1] = temp;

							map[x][y].setPos(x, y);
							map[x - 1][y + 1].setPos(x - 1, y + 1);
							list[i].setPos(x - 1, y + 1);
							list[map[x][y].num].setPos(x, y);

							break;
						}
					} else {
						list[i].dir = 1;
						map[x][y].dir = 1;
						cnt++;
					}
				}
			}
		}
		System.out.println("==========물고기 움직임 후===============");
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				if (map[i][j] == null) {
					System.out.print(0 + "\t");
				} else {
					System.out.print(map[i][j].num + "\t");
				}
			}
			System.out.println();
		}
		
		return map;
	}

	static void printShark(Fish[][] map) {
		System.out.println("===상어 움직임====" + shark.x + "::" + shark.y);
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				if (map[i][j] == null) {
					System.out.print(0 + "\t");
				} else {
					System.out.print(map[i][j].num + "\t");
				}
			}
			System.out.println();
		}
	}

	static void sharkMove(int sum, int x, int y, int dir, int flag, Fish[][] map) {

		if (flag == 0) {
			System.out.println("sum :: "+sum);
			if (max < sum) {
				max = sum;
				System.out.println("max :: "+max);
			}
			return;
		}
		for (int i = 1; i < 4; i++) {
			if (dir == 1) {
				int nx = x - i;
				int ny = y;
				if (nx < 0 || map[nx][ny] == null) {
					continue;
				} else {
					int ndir = map[nx][ny].dir;
					shark.setPos(dir, nx, ny);
					sum += map[nx][ny].num;
					list[map[nx][ny].num] = null;
					map[nx][ny] = null;
					printShark(map);
					Fish[][] copyMap = copy(map);
					sharkMove(sum, nx, ny, ndir, 1, fishMove(copyMap));
				}
			} else if (dir == 2) {
				int nx = x - i;
				int ny = y - i;
				if ((nx < 0) || (ny < 0) || map[nx][ny] == null) {
					continue;
				} else {
					int ndir = map[nx][ny].dir;
					shark.setPos(dir, nx, ny);
					sum += map[nx][ny].num;
					list[map[nx][ny].num] = null;
					map[nx][ny] = null;
					printShark(map);
					Fish[][] copyMap = copy(map);
					sharkMove(sum, nx, ny, ndir, 1, fishMove(copyMap));
				}
			} else if (dir == 3) {
				int nx = x;
				int ny = y - i;
				if ((ny < 0) || map[nx][ny] == null) {
					continue;
				} else {
					int ndir = map[nx][ny].dir;
					shark.setPos(dir, nx, ny);
					sum += map[nx][ny].num;
					list[map[nx][ny].num] = null;
					map[nx][ny] = null;
					printShark(map);
					Fish[][] copyMap = copy(map);
					sharkMove(sum, nx, ny, ndir, 1, fishMove(copyMap));
				}
			} else if (dir == 4) {
				int nx = x + i;
				int ny = y - i;
				if ((ny < 0) || (nx > 3) || map[nx][ny] == null) {
					continue;
				} else {
					int ndir = map[nx][ny].dir;
					shark.setPos(dir, nx, ny);
					sum += map[nx][ny].num;
					list[map[nx][ny].num] = null;
					map[nx][ny] = null;
					printShark(map);
					Fish[][] copyMap = copy(map);
					sharkMove(sum, nx, ny, ndir, 1, fishMove(copyMap));
				}
			} else if (dir == 5) {
				int nx = x + i;
				int ny = y;
				if ((nx > 3) || map[nx][ny] == null) {
					continue;
				} else {
					int ndir = map[nx][ny].dir;
					shark.setPos(dir, nx, ny);
					sum += map[nx][ny].num;
					list[map[nx][ny].num] = null;
					map[nx][ny] = null;
					printShark(map);
					Fish[][] copyMap = copy(map);
					sharkMove(sum, nx, ny, ndir, 1, fishMove(copyMap));
				}
			} else if (dir == 6) {
				int nx = x + i;
				int ny = y + i;
				if ((nx > 3) || (ny > 3) || map[nx][ny] == null) {
					continue;
				} else {
					int ndir = map[nx][ny].dir;
					shark.setPos(dir, nx, ny);
					sum += map[nx][ny].num;
					list[map[nx][ny].num] = null;
					map[nx][ny] = null;
					printShark(map);
					Fish[][] copyMap = copy(map);
					sharkMove(sum, nx, ny, ndir, 1, fishMove(copyMap));
				}
			} else if (dir == 7) {
				int nx = x;
				int ny = y + i;
				if ((ny > 3) || map[nx][ny] == null) {
					continue;
				} else {
					int ndir = map[nx][ny].dir;
					shark.setPos(dir, nx, ny);
					sum += map[nx][ny].num;
					list[map[nx][ny].num] = null;
					map[nx][ny] = null;
					printShark(map);
					Fish[][] copyMap = copy(map);
					sharkMove(sum, nx, ny, ndir, 1, fishMove(copyMap));
				}
			} else if (dir == 8) {
				int nx = x - i;
				int ny = y + i;
				if ((nx < 0) || (ny > 3) || map[nx][ny] == null) {
					continue;
				} else {
					int ndir = map[nx][ny].dir;
					shark.setPos(dir, nx, ny);
					sum += map[nx][ny].num;
					list[map[nx][ny].num] = null;
					map[nx][ny] = null;
					printShark(map);
					Fish[][] copyMap = copy(map);
					sharkMove(sum, nx, ny, ndir, 1, fishMove(copyMap));
				}
			}
		}
		sharkMove(sum, x, y, dir, 0, map);
	}

	static Boolean SharkCheck(int x, int y) {
		boolean flag = true;
		if (shark.x == x && shark.y == y) {
			flag = false;
		}
		return flag;
	}

	static class Fish {
		int num;
		int dir;
		int x;
		int y;

		Fish(int num, int dir, int x, int y) {
			this.num = num;
			this.x = x;
			this.y = y;
			this.dir = dir;
		}

		void setPos(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	static class Shark {
		int dir;
		int x;
		int y;

		Shark(int dir, int x, int y) {
			this.x = x;
			this.y = y;
			this.dir = dir;
		}

		void setPos(int dir, int x, int y) {
			this.dir = dir;
			this.x = x;
			this.y = y;
		}
	}
	
    public static Fish[][] copy(Fish[][] map) {
        if (map == null) {
            return null;
        }
 
        Fish[][] copy = new Fish[map.length][];
        for (int i = 0; i < map.length; i++) {
            copy[i] = Arrays.copyOf(map[i], map[i].length);
        }
 
        return copy;
    }
	
}
