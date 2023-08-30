#include <iostream>
#include <algorithm>
#include <vector>
#include <math.h>
#include <stack>
#include <queue>
#include <memory.h>
using namespace std;

int n, people[12];
bool selected[12], visited[12];
vector <int> v[12];
int num, to,a,b,res= 987654321;

void dfs(int here, bool condition) {
	visited[here] = true;
	if (condition == true)a += people[here];
	else if (condition == false)b += people[here];

	for (int there : v[here]) {
		if (visited[there] == true || selected[there]!= condition)continue;
		dfs(there, condition);
	}
}

void go(int cnt) {
	if (cnt == n + 1) {
		 a=0, b=0;
		fill(visited, visited + 12, false);

		for (int i = 1; i <= n; i++) {
			if (visited[i]==false && selected[i] == true) {
				dfs(i, true);
				break;
			}
		}

		

		for (int i = 1; i <= n; i++) {
			if (visited[i] == false && selected[i] == false) {
				// cout << "B" << ": ";
				dfs(i, false);
				// cout << "\n";
				break;
			}
		}

		int flag = 1;
		for (int i = 1; i <= n; i++) {
			if (visited[i] == false) {
				flag = 0;
				break;
			}
		}
		if (flag == 1) {
			// cout << a << " " << b << "\n";
			res = min(res, abs(a-b));
		}

	}
	else {
		selected[cnt] = true;
		go(cnt + 1);
		selected[cnt] = false;
		go(cnt + 1);
	}
}

int main(){
	cin >> n;

	for (int i = 1; i <= n; i++) {
		cin >> people[i];
	}

	for (int from = 1; from <= n; from++) {
		cin >> num;
		for (int i = 0; i < num; i++) {
			cin >> to;
			v[from].push_back(to);
		}
	}

	go(1);

	if (res == 987654321) res = -1;
	cout << res << "\n";

	return 0;
}

