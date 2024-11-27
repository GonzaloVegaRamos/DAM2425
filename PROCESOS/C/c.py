import math  # Importa el módulo de matemáticas

# Representación del qubit en estado |0⟩
qubit = [1, 0]  # [amplitud para |0⟩, amplitud para |1⟩]

# Definir la puerta Hadamard manualmente multiplicando cada elemento
H = [[1 / math.sqrt(2), 1 / math.sqrt(2)], 
     [1 / math.sqrt(2), -1 / math.sqrt(2)]]  # Matriz Hadamard escalada

# Función para multiplicar una matriz 2x2 por un vector 2x1
def matrix_multiply(matrix, vector):
    return [matrix[0][0] * vector[0] + matrix[0][1] * vector[1],
            matrix[1][0] * vector[0] + matrix[1][1] * vector[1]]

# Aplicar la puerta Hadamard al qubit
qubit = matrix_multiply(H, qubit)

# Calcular probabilidades
prob_0 = abs(qubit[0]) ** 2
prob_1 = abs(qubit[1]) ** 2

# Medir el qubit (colapsar a 0 o 1)
import random
result = "0" if random.random() < prob_0 else "1"
print(f"Resultado de la medición: {result}")
