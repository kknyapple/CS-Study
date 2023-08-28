package algorithm;

import java.util.*;
import java.io.*;

//게리맨더링
public class Main_17471_문혜린 {
	static int N; //구역의 개수
	static int[] per; //구역 인구
	static ArrayList<ArrayList<Integer>> graph; //인접 리스트
	static boolean isSelected[];
	static int min = Integer.MAX_VALUE; //최소 인구 차
	
	//부분 집합 -> 선거구 나누기
	public static void subSet(int cnt) {
		if(cnt == N) {
			ArrayList<Integer> local1 = new ArrayList<>(); //뽑힌
			ArrayList<Integer> local2 = new ArrayList<>(); //안뽑힌
			
			for (int i = 1; i < N+1; i++) {
				if(isSelected[i]) {
					local1.add(i);
				}
				else {
					local2.add(i);
				}
			}
			
			//선거구 2개로 나뉘지 않은 경우
			if(local1.size()==N || local2.size()==N) {
				return;
			}
			//선거구 2개로 나뉜 경우
			else {
				//올바르게 나뉘었는지 확인
				if(checked(local1) && checked(local2)) {
					int sum1 = 0;
					int sum2 = 0;
					for (int i = 1; i < N+1; i++) {
						if(isSelected[i]) {
							sum1 += per[i];
						}
						else {
							sum2 += per[i];
						}
					}
					min = Math.min(min, Math.abs(sum1-sum2));
				}
			}
			
			
		}
		else {
			isSelected[cnt] = true;
			subSet(cnt+1);
			isSelected[cnt] = false;
			subSet(cnt+1);
		}
	}
	//같은 선거구 인접 구역인지 확인
	public static boolean checked(ArrayList<Integer> list) {
		boolean visited[] = new boolean[N+1];
		Queue<Integer> q = new LinkedList<>();
		//초기 구역
		q.add(list.get(0));
		while(!q.isEmpty()) {
			int now = q.remove();
			visited[now] = true; //방문 처리
			for(Integer next:list) { //선거구 구역끼리 연결됐는지 확인
				if(!visited[next]) { //아직 방문 안했을 경우
					if(graph.get(now).contains(next)) { //현재 구역과 다른 구역이 연결되어 있으면
						q.add(next); //큐 삽입
					}
				}
			}
		}
		//방문한 구역 수가 선거구 구역 수와 동일할 경우 인접함
		int cnt = 0;
		for (int i = 1; i < N+1; i++) {
			if(visited[i]) {
				cnt++;
			}
		}
		if(cnt == list.size()) {
			return true;
		}
		
		return false;
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine()); //구역의 개수
		st = new StringTokenizer(br.readLine());
		//구역의 인구
		per = new int[N+1];
		for (int i = 1; i < N+1; i++) {
			per[i] = Integer.parseInt(st.nextToken());
		}
		//인접 리스트
		graph = new ArrayList<ArrayList<Integer>>();
		for (int i = 0; i < N+1; i++) {
			graph.add(new ArrayList<Integer>());
		}
		for (int i = 1; i < N+1; i++) {
			st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			for (int j = 0; j < n; j++) {
				graph.get(i).add(Integer.parseInt(st.nextToken()));
			}
		}
		
		isSelected = new boolean[N+1];
		subSet(1);
		//두 선거구로 나눌 방법 없는 경우
		if(min == Integer.MAX_VALUE) {
			System.out.println(-1);
		}
		else{
			System.out.println(min);
		}
		
	}

}
