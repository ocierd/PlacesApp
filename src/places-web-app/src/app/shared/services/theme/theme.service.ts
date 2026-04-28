import { Injectable, signal, Signal, WritableSignal } from '@angular/core';


@Injectable({
  providedIn: 'root',
})
export class ThemeService {

  private themes: string[] = ['places-light-theme', 'places-dark-theme'];
  private currentTheme: WritableSignal<'places-light-theme' | 'places-dark-theme'> = signal('places-light-theme');

  getCurrentTheme(): Signal<'places-light-theme' | 'places-dark-theme'> {
    return this.currentTheme.asReadonly();
  }

  constructor() {}

  setTheme(theme: 'places-light-theme' | 'places-dark-theme'): void {
    this.currentTheme.set(theme);
  }

  getThemes(): string[] {
    return this.themes;
  }

}
