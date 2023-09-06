# Partitioning

## 파티셔닝 (Partitioning)

> 데이터베이스의 테이블을 분할하여 분산 저장하는 기술

## 장점

- DML의 성능이 개선된다.
- 유지보수성, 가용성이 향상된다.

## 단점

- JOIN 복잡도가 증가한다.
- 분산으로 관리와 무결성 유지가 어려워진다.

## 종류

1. 수직 분할(Vertical Partitioning)

<img src="https://github.com/Ahrang777/CS-Study/assets/72875528/9c9c63bf-adb9-4167-82b3-0e943ed3deed" width="80%" />

> 테이블의 Column을 분할하여 여러 개의 서로 다른 테이블로 나누는 방법

- 만약 한 테이블에 수많은 컬럼이 존재할 경우, 로우 체이닝과 로우 마이그레이션이 발생하게 된다.
- 로우 체이닝과 로우 마이그레이션이 발생하여 많은 블록을 사용하게 되면, 불필요한 I/O가 발생하여 성능이 저하된다.

### 로우 체이닝(Row Chaining)

- 길이가 너무 커서 하나의 블록에 저장되지 못하고 다수의 블록에 나누어져 저장

### 로우 마이그레이션(Row Migration)

- 수정된 데이터를 해당 데이터 블록에 저장하지 못하고 다른 블록의 빈 공간에 저장

1. 수평 분할(Horizontal Partitioning)

<img src="https://github.com/Ahrang777/CS-Study/assets/72875528/36cb1fa2-106b-4619-b4ba-e46ed333f318" width="80%" />

> 데이터베이스에서 테이블의 Row를 분할하여 여러 개의 서로 다른 테이블로 나누는 방법

## 데이터 분할 기준(수평 분할)

<img src="https://github.com/Ahrang777/CS-Study/assets/72875528/0efcf79e-43e0-4400-8386-b397ac3fda33" width="80%" />

1. 범위 분할(Range Partitioning)
   - 연속적인 값의 범위를 기준으로 하여 분할한다.
   - 우편 번호, 날짜 분기 등 데이터에 적합하다.
2. 목록 분할(List Partitioning)
   - 데이터 값이 특정 목록에 포함된 경우, 데이터를 분리한다.
   - 나라, 지역 등의 데이터에 적합하다.
3. 해시 분할(Hash Partitioning)
   - Key값 등 특정 Column의 값을 Hashing하여 분할한다.
   - 균등한 데이터 분할이 가능하다.
   - 범위가 없는 데이터에 적합하다.
4. 합성 분할(Composite Partitioning)
   - 위 종류 중 2개 이상을 사용하여 분할한다.

## 참고

https://spiderwebcoding.tistory.com/14  
https://yunamom.tistory.com/291    
https://aiday.tistory.com/123  
