# B-트리

이진트리에서 발전되어 모든 리프노드들이 같은 레벨을 가질 수 있도록 자동으로 밸런스를 맞추는 트리입니다. 정렬 순서를 보장하고 멀티레벨 인덱싱을 통한 빠른 검색을 가능하게 합니다. 

M차 B트리 : 최대 M개의 자식을 가질 수 있는 B트리(각 노드에서 최대 M개의 자식)

![B-tree0](https://github.com/Ahrang777/Ahrang777.github.io/assets/59478159/596363e5-8ea6-4931-980f-289ce3d5ede2)


## 특징

- 노드는 최대 M개에서 M/2개의 자식을 가질 수 있습니다. 
- 노드에서 최대 M - 1개에서 M/2 - 1개의 키를 포함할 수 있습니다. 
- 즉, 노드의 키가 X개 라면 자식의 수는 X+1개 입니다. 
- 이진탐색 트리 처럼 각 key의 왼쪽은 항상 key보다 작은 값을 갖고 오른쪽은 큰 값을 갖습니다. 



## 키 검색

루트노드에서 시작하여 하향식으로 검색을 수행합니다. 

찾는 값 : k

1. 루트 노드에서 시작하여 key들을 순회하면서 검사합니다. 
   1. k 와 같은 key를 찾았다면 종료
   2. k와 key들의 대소관계를 비교하고 k가 어떤 key들 사이에 들어간다면 해당 key들 사이의 자식노드로 내려갑니다. 
2. 해당 과정을 리프노드에 도달할 때까지 반복합니다. 리프노드에도 k와 같은 key가 없다면 검색이 실패합니다. 



![B-tree](https://github.com/Ahrang777/Ahrang777.github.io/assets/59478159/79962628-ac36-4fa3-b91b-37db1f29544d)



![B-tree1](https://github.com/Ahrang777/Ahrang777.github.io/assets/59478159/bffbb2d1-6882-4a1a-a0aa-ea82409ba3d9)





## 키 삽입

1. 삽입하기 적절한 리프노드를 검색
2. 필요한 경우 노드를 분할

노드의 검색은 하향식으로 이루어지지만 분할은 상향식으로 이루어집니다. 

### 분할이 일어나지 않는 경우

리프노드가 가득차지 않아서 단순 삽입

![insert1](https://github.com/Ahrang777/Ahrang777.github.io/assets/59478159/9578e8b9-2801-41dd-92cc-36bcb14ca5b4)



### 분할이 일어나는 경우

리프노드에 key값들이 가득 찬 경우, 노드를 분할해야 합니다. 

1. 오름차순으로 요소 삽입. 노드가 담을 수 있는 최대 key 개수를 초과
2. 중앙값에서 분할을 수행합니다. 중앙값은 부모 노드로 병합되거나 새로 생성됩니다. 왼쪽 키들은 왼쪽 자식으로, 오른쪽 키들은 오른쪽 자식으로 분할
3. 부모 노드를 검사해서 또 가득 찬 경우, 다시 부모노드에서 위 과정 반복



![insert2](https://github.com/Ahrang777/Ahrang777.github.io/assets/59478159/1a304031-11ad-45b2-909a-02653e8f3ce4)

![insert3](https://github.com/Ahrang777/Ahrang777.github.io/assets/59478159/7726292b-f63e-42eb-be80-1cf1a7d859f3)

![insert4](https://github.com/Ahrang777/Ahrang777.github.io/assets/59478159/3b0cc870-4e30-46a0-a822-87f3c3f86ea0)



## 키 삭제

1. 삭제할 키가 있는 노드 검색
2. 키 삭제
3. 필요하다면 트리 균형 조정



간단하게 표현하기 위한 용어 정리

- inorder predecessor : 노드의 왼쪽 자손에서 가장 큰 key
- inorder successor : 노드의 오른쪽 자손에서 가장 작은 key
- 부모 key : 부모노드의 key들 중 왼쪽 자식으로 본인 노드를 가지고 있는 key값. 단, 마지막 자식노드의 경우에는 부모의 마지막 key입니다. 



### 1. 삭제할 key가 리프노드에 있는 경우

#### 1-1) 현재 노드의 key개수가 최소 key 개수보다 큰 경우

k가 삭제되어도 구조는 변함 없기에 단순 삭제

![delete1](https://github.com/Ahrang777/Ahrang777.github.io/assets/59478159/07ac7eb6-920b-4b0d-886f-7b6d1f89020f)



#### 1-2) 왼쪽 또는 오른쪽 형제 노드의 key가 최소 key 개수 이상

즉, 현재 k에 해당하는 값을 삭제하여도 왼쪽 또는 오른쪽 형제 노드의 key를 가져와서 구조에 변함이 없는 경우

1. 부모 key 값으로 k를 대체
2. 최소key 개수 이상을 가진 형제 노드가 왼쪽이라면 해당 형제노드의 가장 큰 값을, 오른쪽 형제라면 가장 작은 값을 부모 key로 대체합니다. 즉, 부모와 차이가 가장 적게 나는 값으로 대체

![delete2](https://github.com/Ahrang777/Ahrang777.github.io/assets/59478159/fc1a042c-8717-4dd4-ba51-809f059e2c7f)

#### 1-3) 왼쪽, 오른쪽 형제 노드의 key가 최소 key개수 && 부모노드의 key가 최소개수 이상

즉, 왼쪽, 오른쪽 형제 노드의 key가 최소 key 개수라서 k가 삭제된 후 일부를 가져올 수 없지만 부모노드의 key가 최소 key개수이상으로 가져올 수 있는 경우입니다. 형제노드에서 가져올 수 없는 상황에 부모노드에서는 가져올 수 있어 key를 가져와 구조의 변함이 없는 경우입니다. 

1. k를 삭제한 후, 부모 key를 형제 노드와 병합합니다.
2. 부모노드의 key개수를 하나 줄이고, 자식 수 역시 하나를 줄여 **B-Tree를 유지**합니다.

![delete3](https://github.com/Ahrang777/Ahrang777.github.io/assets/59478159/7cce1700-4a63-4efb-a430-34afcd5494ef)



#### 1-4) 삭제할 key가 있는 리프노드, 형제, 부모 노드의 key개수가 모두 최소 key 개수인 경우

부모노드를 루트 노드로한 트리의 높이가 줄어드는 경우이기 때문에 재구조화 과정이 필요합니다. 



### 2. 삭제할 key 가 리프노드가 아닌 노드에 있고, 해당 노드나 자식노드의 키가 최소 key 개수보다 많은 경우

1. 현재 노드의 inorder predecessor 또는 inorder successor 와 k의 자리를 바꿉니다. 
2. 리프노드의 k를 삭제하게 되면, 리프노드가 삭제 되었을 때의 조건으로 변합니다. 이후 작업은 1.의 경우에 맞게 진행됩니다. 



![delete4](https://github.com/Ahrang777/Ahrang777.github.io/assets/59478159/bafbb190-4ec7-45eb-8672-d356d1525ec2)

### 3. 삭제할 key인 k가 내부 노드에 있고, 노드의 key개수가 최소 key개수만큼, 노드의 자식 key 개수도 모두 최소 key 개수인 경우

k를 삭제하면 트리의 높이가 줄어들어 재구조화가 일어나는 경우입니다. 

1. k를 삭제하고, k의 양쪽 자식을 병합하여 하나의 노드로 만듭니다. 

2. k의 부모 key를 인접한 형제 노드에 붙입니다. 이후, 이전에 병합했던 노드를 자식 노드로 설정합니다. 

3. 해당 과정을 수행했을 때 부모노드의 개수에 따라 이후 과정이 달라집니다. 

   1. 새로 구성된 인접 형제노드의 키가 최대 key 개수를 넘었다면 삽입노드의 노드 분할 과정을 수행합니다. 
   2. 만약 인접 형제노드가 새로 구성되더라도 원래 k의 부모노드가 최소 key의 개수보다 작아진다면, 부모 노드에 대해 2번 과정부터 다시 수행합니다. 

   

![delete5](https://github.com/Ahrang777/Ahrang777.github.io/assets/59478159/62912e44-8ee8-4473-a329-67206afdfb59)



다른 예시 >> 같은 레벨로 만들기 위함

![delete6](https://github.com/Ahrang777/Ahrang777.github.io/assets/59478159/85c44307-7e6e-4913-986e-380d1dd3c8da)







참고

[https://velog.io/@emplam27](https://velog.io/@emplam27/%EC%9E%90%EB%A3%8C%EA%B5%AC%EC%A1%B0-%EA%B7%B8%EB%A6%BC%EC%9C%BC%EB%A1%9C-%EC%95%8C%EC%95%84%EB%B3%B4%EB%8A%94-B-Tree)

