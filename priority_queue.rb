class PriorityQueue
  def initialize
    @elements = []
  end

  def empty?
    elements.size.zero?
  end

  def <<(element)
    elements << element
    heapify_up(elements.size - 1)
    elements
  end

  def pop
    raise 'Cannot remove from empty queue' if empty?
    exchange(0, elements.size - 1)
    max = elements.pop
    heapify_down(0)
    max
  end

  private

  def heapify_up(index)
    return if index <= 0
    parent_index = index / 2
    return if elements[parent_index] > elements[index]
    exchange(index, parent_index)
    heapify_up(parent_index)
  end

  def heapify_down(index)
    child_index = calculate_child_index(index)
    return unless child_index < elements.size
    return unless elements[child_index] > elements[index]
    exchange(index, child_index)
    heapify_down(child_index)
  end

  def calculate_child_index(index)
    child_index = index.zero? ? 1 : index * 2
    if child_index + 1 < elements.size && elements[child_index] < elements[child_index + 1]
      child_index += 1
    end
    child_index
  end

  def exchange(source, target)
    elements[source], elements[target] = elements[target], elements[source]
  end

  attr_reader :elements
end
