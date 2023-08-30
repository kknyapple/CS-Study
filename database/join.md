## Join

### Join

- 데이터베이스에서 둘 이상의 테이블을 연결해서 테이블을 검색하는 방법이다.
- 서로 관계 있는 데이터가 여러 테이블로 나뉘어 저장되므로 각 테이블에 저장된 데이터를 효과적으로 검색하기 위해 필요하다.

### Join의 종류

- INNER JOIN
- OUTER JOIN
- CROSS JOIN
- SELF JOIN

## INNER JOIN

### INNER JOIN

```sql
SELECT * FROM TableA A
INNER JOIN TableB B ON
A.key = B.key
```

- 왼쪽 테이블과 오른쪽 테이블의 두 행이 모두 일치하는 행이 있는 부분만 출력한다.

## OUTER JOIN

### LEFT JOIN

```sql
SELECT * FROM TableA A
LEFT JOIN TableB B ON
A.key = B.key WHERE B.key IS NULL
```

- 왼쪽 테이블의 모든 행이 결과 테이블에 출력된다.

### RIGHT JOIN

```sql
SELECT * FROM TableA A
RIGHT JOIN TableB B ON
A.key = B.key WHERE A.key IS NULL
```

- 오른쪽 테이블의 모든 행이 결과 테이블에 출력된다.

### FULL OUTER JOIN

```sql
SELECT * FROM TableA A
FULL OUTER JOIN TableB B ON
A.key = B.key
```

- 왼쪽 테이블과 오른쪽 테이블이 가지고 있는 모든 값들을 출력해준다.
- LEFT OUTER JOIN과 RIGHT OUTER JOIN의 결과 값을 합친 것이다.

## CROSS JOIN

### CROSS JOIN

- 두 테이블 간의 가능한 모든 경우의 수에 대한 결과를 보여준다. 카디널리티 곱.
- 두 테이블의 모든 행들을 서로 교차하여 곱한다. ( A 테이블 행 개수 x B 테이블 행 개수 만큼의 행이 출력됨)

## SELF JOIN

### SELF JOIN

- 자기 자신과 결합하는 방식으로 자기 자신의 테이블을 참조한다.

## Join 예시

### 테이블 예시

![image](https://github.com/kknyapple/CS-Study/assets/72698829/1572a36f-649b-44e2-92fc-ed6a694c6db4)

### INNER JOIN

![image](https://github.com/kknyapple/CS-Study/assets/72698829/39ac5a23-36c3-43c4-a82f-15acdd395579)

```sql
SELECT A.상품코드 상품코드, A.상품명 상품명, B.재고수량 재고수량
	FROM TableA as A
    	INNER JOIN TableB as B
    	ON A.상품코드 = B.상품코드
```

### LEFT JOIN

![image](https://github.com/kknyapple/CS-Study/assets/72698829/138e2a7e-7ed5-4ab2-ae27-0b3090cdaee7)

```sql
SELECT A.상품코드 상품코드, A.상품명 상품명, B.재고수량 재고수량 
	FROM TableA as A       
    	LEFT JOIN TableB as B   
    	ON A.상품코드 = B.상품코드
```

### RIGHT JOIN

![image](https://github.com/kknyapple/CS-Study/assets/72698829/18d49002-d2bd-4c6a-8f40-05245d8ea713)

```sql
SELECT A.상품코드 상품코드, A.상품명 상품명, B.재고수량 재고수량 
	FROM TableA as A       
    	RIGHT JOIN TableB as B   
    	ON A.상품코드 = B.상품코드
```

### FULL JOIN

![image](https://github.com/kknyapple/CS-Study/assets/72698829/8d74f997-3545-43a8-9f47-a1ff9ff787af)

```sql
SELECT A.상품코드 상품코드, A.상품명 상품명, B.재고수량 재고수량 
	FROM TableA as A       
    	FULL JOIN TableB as B   
    	ON A.상품코드 = B.상품코드
```

### CROSS JOIN

![image](https://github.com/kknyapple/CS-Study/assets/72698829/d48ef884-bb0a-429e-81c1-59e2439e2cf8)

### SELF JOIN

![image](https://github.com/kknyapple/CS-Study/assets/72698829/c0433b59-f7e1-4e2a-ab86-c9ec2d3beecc)

![image](https://github.com/kknyapple/CS-Study/assets/72698829/5450d28f-80ed-4eba-80a4-59782c9faf21)

```sql
SELECT A.ID, A.Name, A.Partner, B.Partner PartName
FROM Star A JOIN Star B 
ON A.Partner = B.ID
```

### 참고

- https://hongcoding.tistory.com/146
- [https://velog.io/@ragnarok_code/DataBase-조인Join이란](https://velog.io/@ragnarok_code/DataBase-%EC%A1%B0%EC%9D%B8Join%EC%9D%B4%EB%9E%80)
- [https://velog.io/@newdana01/Database-테이블-조인-이해하기](https://velog.io/@newdana01/Database-%ED%85%8C%EC%9D%B4%EB%B8%94-%EC%A1%B0%EC%9D%B8-%EC%9D%B4%ED%95%B4%ED%95%98%EA%B8%B0)
- [https://hongong.hanbit.co.kr/sql-기본-문법-joininner-outer-cross-self-join/](https://hongong.hanbit.co.kr/sql-%EA%B8%B0%EB%B3%B8-%EB%AC%B8%EB%B2%95-joininner-outer-cross-self-join/)
- https://sql-joins.leopard.in.ua/
