# Python program to print DFS traversal from a given given graph
# Visits all verticies and computes their mean and variance
from collections import defaultdict 
#from random import randint
# This class represents a directed graph using adjacency list representation 
class Graph: 
    
    # Constructor 
    def __init__(self): 
        # default dictionary to store graph
        self.graph = defaultdict(list)  
  
    # function to add an edge to graph 
    def addEdge(self,u,v): 
        self.graph[u].append(v) 
  
    # A function used by DFS 
    def DFSUtil(self,v,visited): 
  
        # Mark the current node as visited and print it 
        visited[v]= True
        print(v)
        global s, c, verticies
        s += v
        verticies.append(v)
        c += 1
        
        # Recur for all the vertices adjacent to this vertex
        for i in self.graph[v]:
            if visited[i] == False: 
                self.DFSUtil(i, visited) 
    
    # The function to do DFS traversal. It uses 
    # recursive DFSUtil() 
    def DFS(self,v): 
        # Mark all the vertices as not visited 
        visited = [False]*(len(self.graph)+1) 
        # Call the recursive helper function to print 
        # DFS traversal 
        self.DFSUtil(v,visited)
  
  
# Driver code 
# Create a graph given in the above diagram 
# Each vertex is labeled with a unique integer L(u) from the set {1,2,...,|V|} 
g = Graph()
g.addEdge(1, 2) 
g.addEdge(2, 3) 
g.addEdge(3, 3) 
g.addEdge(1, 4)
g.addEdge(4, 4)
g.addEdge(2, 5)
g.addEdge(5, 3)
g.addEdge(3, 4)
g.addEdge(5, 1)

# global sum and count variables to compute average
s = 0.0
c = 0
verticies = [] 

print("Following is DFS from (starting from vertex 5)")
g.DFS(5)

v_mean = s/c
print("Vertex Mean: " + str(v_mean))

#reset value of s for new sum operation
s = 0.0
for v in verticies:
    s += (v - v_mean)**2

v_variance = s/len(verticies)
print("Vertex Variance " + str(v_variance))
