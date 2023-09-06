# 샤딩

## 샤딩 (Sharding)

> 동일한 스키마를 가지고 있는 데이터를 다수의 데이터베이스에 분산하여 저장하는 기법

## 수평 파티셔닝과 샤딩의 차이점

<img src="https://github.com/Ahrang777/CS-Study/assets/72875528/75e199f4-4795-4084-b777-78451f877d01" width="70%" />

- 수평 파티셔닝 : 동일한 서버에 저장

<img src="https://github.com/Ahrang777/CS-Study/assets/72875528/75e199f4-4795-4084-b777-78451f877d01" width="70%" />

- 샤딩 : 서로 다른 서버에 분산하여 저장, 데이터베이스 차읜의 수평 확장(scale-out)

## 종류

1. Hash Sharding

   <img src="https://github.com/Ahrang777/CS-Study/assets/72875528/fb8d4817-ed23-49aa-8f4e-b2fcb4765d6c" width="80%" />

   - Shard Key는 pk를 Hashing하여 결정한다.
   - Range Sharding에 비해 데이터가 균일하게 분산된다.
   - 클러스터가 포함하는 Node 갯수를 변경하면 해시크기가 변경되고 key 또한 변경되어 ReSharding이 필요하다.
   - 일정 수준에서 유지될 것으로 예상되는 데이터 성격을 가진 곳에 적절하다.

2. Range Sharding

   <img src="https://github.com/Ahrang777/CS-Study/assets/72875528/c8f6fefc-aad7-4c0d-bb00-30277bacc145" width="80%" />

   - Hash Sharding에 비해 기본적으로 증설작업에 재정렬 비용이 들지 않고, 증설작업에 비용이 크지 않다.
   - 활성유저가 몰린 일부 DB에 데이터가 편향될 수 있다.

## 참고
 
https://aiday.tistory.com/123  
https://spiderwebcoding.tistory.com/14  
