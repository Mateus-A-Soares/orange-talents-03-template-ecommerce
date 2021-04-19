package br.com.zupacademy.mateus.mercadolivre.compra.gateway;


import br.com.zupacademy.mateus.mercadolivre.compra.Compra;

public abstract class GatewayRetornoEvent {
	
	protected Compra compra;
	
	public void execute(Compra compra) {
		this.compra = compra;
		if(compra.existePagamentoSucedido()) {
			this.successful();
			return;
		}
		this.fail();
	}

	protected void successful() {};
	
	protected void fail() {};
}
