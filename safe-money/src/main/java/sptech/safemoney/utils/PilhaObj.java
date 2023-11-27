package sptech.safemoney.utils;

public class PilhaObj<T> {
    private T[] pilha;
    private int topo;

    // 02) Construtor
    public PilhaObj(int capacidade) {
        pilha = (T[]) new Object[capacidade];
        topo = -1;
    }

    // 03) MÃ©todo isEmpty
    public Boolean isEmpty() {
        return topo == -1 ?
                true : false;
    }

    // 04) MÃ©todo isFull
    public Boolean isFull() {
        return topo == (pilha.length - 1) ?
                true : false;
    }

    // 05) MÃ©todo push
    public void push(T info) {
        if (isFull()) {
            throw new IllegalStateException();
        } else {
            pilha[++topo] = info;
        }
    }

    // 06) MÃ©todo pop
    public T pop() {
        var aux = pilha[topo];
        pilha[topo--] = null;

        return aux;
    }

    // 07) MÃ©todo peek
    public T peek() {
        if (isEmpty()) {
            return null;
        } else {
            return pilha[topo];
        }
    }

    // 08) MÃ©todo exibe
    public void exibe() {
        for (int i = topo; i > -1; i--) {
            System.out.println(pilha[i]);
        }
    }


    //Getters & Setters (manter)
    public int getTopo() {
        return topo;
    }

    public T[] getPilha() {
        return pilha;
    }
}
